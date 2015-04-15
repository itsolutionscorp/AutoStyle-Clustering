class Bob
  
  
  def hey(something)
    @something = something.to_s
      if yelled_question?
        "Woah, chill out!"
        elsif question?
          "Sure."
          elsif yelling?
            "Woah, chill out!"
            elsif silent?
              "Fine. Be that way!"
              else
                "Whatever."
    end
  end
  
    def yelled_question?
      @something == @something.upcase && @something.end_with?('?')
    end
    
    def question?
        @something.end_with?('?')
    end
    
    def yelling?
      @something == @something.upcase && @something != ''
    end
    
    def silent?
      @something == '' 
    end
end
