class Bob
    def initialize
        ## answers sorted by priority
        @answers = {
            "Fine. Be that way!" => {
                :functions => [
                    Proc.new { |msg| msg.strip.empty? }
                ]
            },
            "Woah, chill out!" =>
            {
                :functions => [
                    Proc.new { |msg| msg == msg.upcase }   
                ]
            },
            "Sure." => {
                :functions => [
                    Proc.new { |msg| msg.end_with?( "?" ) }
                ]
            }
        }
        @default_answer = "Whatever."
    end

    def hey(msg)
        @answers.each do |k, v|
            validator = FuncValidator.new(v)
            if validator.validate(msg)
                return k
            end
        end
        return @default_answer
    end
end

class FuncValidator
    def initialize(validator)
        @validator = validator[:functions]
    end

    def validate(string)
        @validator.each do |function|
            return false unless function.call(string)
        end
        true
    end
end
