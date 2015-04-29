class Bob
    # def initialize
        
    # end
    def hey( hello )
        
        case


        
        when hello == hello.upcase && hello != hello.downcase # hello.upcase == hello
            'Woah, chill out!'

        when hello[-1] =="?"
            "Sure."

        when hello.strip.empty?
            'Fine. Be that way!'

        else  
            'Whatever.' 
        end
    end
end
