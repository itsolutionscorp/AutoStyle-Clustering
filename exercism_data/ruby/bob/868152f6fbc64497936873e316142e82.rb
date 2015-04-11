# Requires Ruby 2.0.

refinements = Module.new do # Don't use standard module syntax so we can store refinements in a local variable.
    refine Object do # Refinement is on Object as message could be nil.
        def quiet?;
            return true if nil?
            strip == ""
        end;
    end

    refine String do
        def shouting?; self == upcase; end
        def asking?;   self =~ /\?$/;  end
    end
end

using refinements

class Bob
    def hey message
        case
        when message.quiet?
            "Fine. Be that way!"
        when message.shouting?
            "Woah, chill out!"
        when message.asking?
            "Sure."
        else
            "Whatever."
        end
    end

end
