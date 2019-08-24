# Eagle Board Schema

What exactly is interesting?

    <eagle>
      <drawing>
        <board>
          <plain>
            <wire> in layer 20 - board dimensions 
            <rectangle> in layers 1 & 16 - fill?
            <hole> - top level hole (mounting)
          <libraries>
            Component footprints
            <library>
              E.g. DIL-8.
              Best to think of this as where the pad configuration is DEFINED
              <package>
                <wire>
                <pad>
          <elements>
            Individual circuit components on the PCB, references the <package> elements above.
            Think of this as where the packages are instantiated.
            <element>
          <signals>
            <signal>

              