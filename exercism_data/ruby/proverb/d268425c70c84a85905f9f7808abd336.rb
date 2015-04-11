class Proverb

    attr_reader :args,
              :qualifier

    def initialize(*args)
        has_qualifier = args.last.is_a?(Hash)
        @args = has_qualifier ? args[0..-2] : args
        @qualifier = has_qualifier ? args.pop[:qualifier] : ""
    end

    def to_s
        result = []
        args.each_with_index do |_, i|
            result << phrase(args[i], args[i+1]) unless i == args.length-1
        end
        result << final()
        result.join("\n")
    end

    def phrase(first, second)
        "For want of a #{first} the #{second} was lost."
    end

    def final
        "And all for the want of a #{qualifier != "" ? qualifier + " " : qualifier}#{args[0]}."
    end
end
