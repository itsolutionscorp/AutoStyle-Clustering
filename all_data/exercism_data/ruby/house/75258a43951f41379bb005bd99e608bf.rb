class House
  def initialize
  end

  def verses(*args)
    @output = ''
    number  = args[0]

    while number <= args[1] do
      @output += verse(number)
      @output += "\n"
      number  += 1
    end

    @output
  end

  def verse(*args)
    @output = ''
    intro

    pairs = %w[buffer in\ the\ house Jack\ built. malt lay rat ate cat killed dog worried 
      cow\ with\ the\ crumpled\ horn
      tossed maiden\ all\ forlorn milked man\ all\ tattered\ and\ torn kissed
      priest\ all\ shaven\ and\ shorn married rooster\ that\ crowed\ in\ the\ morn woke
      farmer\ sowing\ his\ corn kept horse\ and\ the\ hound\ and\ the\ horn belonged\ to]
   
      if args[0] == 1
      else
        args.each do |verse|
          verse = verse.to_i
          while verse > 1
            verb = pairs[( verse*2 )]
            noun = pairs[((verse*2) - 1)]
            construct(noun, verb)
            verse -= 1
          end
        end
      end

    args.include?(1) ? outro(first = true) : outro
    @output
  end

  private

  def intro
    @output << "This is "
  end

  def outro(first = false)
    if first == true
      @output << "the house that Jack built.\n"
    else
    @output << "in the house that Jack built.\n"
    end
  end

  def construct(noun, verb)
    @output << "the #{noun} that #{verb} "
  end
end
