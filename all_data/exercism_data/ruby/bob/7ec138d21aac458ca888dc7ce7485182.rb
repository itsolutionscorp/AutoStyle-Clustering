  class Bob

    YELL     =  /\A[^a-z]*[A-Z][^a-z]*\z/
    QUESTION =  /\?\z/
    NADA     =  /\A\s*\z/

    # puts "Ask Bob a Question"
    # ques = gets.chomp


    def hey(ques)

      case ques

      when YELL
         "Woah, chill out!"  
      when QUESTION
         "Sure."
      when NADA
         "Fine. Be that way!"
      else
         "Whatever."
      end

    end

  end
