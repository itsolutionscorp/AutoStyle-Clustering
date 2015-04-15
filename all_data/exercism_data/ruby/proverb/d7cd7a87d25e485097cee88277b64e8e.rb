class Proverb
    def initialize(*missing_things, qualifier: nil)
        @missing_things = missing_things
        @qualifier = qualifier + " " if qualifier
        make_proverb
    end

    def to_s
        @proverb
    end

    private

    def make_proverb
        @proverb = ""
        (0...@missing_things.length-1).each do |index|
            @proverb << line_from(@missing_things[index], @missing_things[index+1])
        end
        @proverb << last_line
    end

    def line_from(wanted, lost)
        "For want of a #{wanted} the #{lost} was lost.\n"
    end

    def last_line
        "And all for the want of a #{@qualifier}#{@missing_things[0]}."
    end
end
