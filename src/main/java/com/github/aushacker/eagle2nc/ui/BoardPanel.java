/*
 * Copyright 2019 Stephen Davies
 *
 * This file is part of eagle2nc.
 *
 * eagle2nc is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * eagle2nc is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with eagle2nc. If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.aushacker.eagle2nc.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;

import com.github.aushacker.eagle2nc.model.Board;
import com.github.aushacker.eagle2nc.model.Layer;
import com.github.aushacker.eagle2nc.model.Via;

/**
 * @author Stephen Davies
 * @since August 2019
 */
public class BoardPanel extends JPanel {

    private static final Map<String, Color> colors;

    static {
        colors = new HashMap<>();
        colors.put("bottom", new Color(0, 119, 174));
        colors.put("pad", new Color(39, 177, 119));
        colors.put("top", new Color(163, 5, 0));
        colors.put("via", new Color(197, 180, 73));
    }

    private Board board;

    private void drawAxes(Graphics2D g) {
        g.setColor(Color.CYAN);
        Stroke savedStroke = g.getStroke();
        g.setStroke(new BasicStroke(0.1f));

        // X axis
        g.draw(new Line2D.Double(-200, 0, 200, 0));
        
        // Y axis
        g.draw(new Line2D.Double(0, -200, 0, 200));
        
        g.setStroke(savedStroke);
    }

    private void drawDimensions(Graphics2D g) {
        Stroke savedStroke = g.getStroke();
        g.setStroke(new BasicStroke(0.1f));

        g.setColor(Color.BLACK);
        Iterator<Point2D.Double> points = board.getDimensions().iterator();
        Point2D.Double start = points.next();
        Point2D.Double current = start;
        
        while (points.hasNext()) {
            Point2D.Double next = points.next();
            g.draw(new Line2D.Double(current, next));
            current = next;
        }

        g.draw(new Line2D.Double(current, start));
        
        g.setStroke(savedStroke);
    }

    private void drawPads(Graphics2D g) {
        Stroke savedStroke = g.getStroke();
        g.setStroke(new BasicStroke(0.0f));

        g.setColor(colors.get("pad"));
        //TODO
        //board.getPads().forEach(p -> g.fill(p.getShape()));

        g.setStroke(savedStroke);
    }

    private void drawTraces(Graphics2D g) {
        Stroke savedStroke = g.getStroke();
        g.setStroke(new BasicStroke(0.0f));

        g.setColor(colors.get("top"));
        board.getSignals()
            .forEach(s ->
                s.getTraces().stream()
                    .filter(t -> t.getLayer() == Layer.TOP)
                    .forEach(t -> g.fill(t.getShape())));
            
        g.setColor(colors.get("bottom"));
        board.getSignals()
            .forEach(s ->
                s.getTraces().stream()
                    .filter(t -> t.getLayer() == Layer.BOTTOM)
                    .forEach(t -> g.fill(t.getShape())));
            
        g.setStroke(savedStroke);
    }

    private void drawVias(Graphics2D g) {
        Stroke savedStroke = g.getStroke();
        g.setStroke(new BasicStroke(0.0f));

        g.setColor(colors.get("via"));
        board.getVias().forEach(v -> g.fill(v.getShape()));

        g.setStroke(savedStroke);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform previous = g2.getTransform();

        g2.translate(50, 500);
        g2.scale(7, -7);

        drawAxes(g2);
        drawDimensions(g2);
        drawTraces(g2);
        //TODO
        //drawPads(g2);
        drawVias(g2);

        g2.setTransform(previous);
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
