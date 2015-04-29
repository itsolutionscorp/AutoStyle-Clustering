class Bob
  REPERTORY =  { [ /\A[^a-z]*\z/, /[A-Z]+/ ]   => "Whoa, chill out!",
                  [ /\?\z/ ]                   => "Sure.",
                  [ /\A[[:space:]]*\z/ ]       => "Fine. Be that way!",
                  [ /\w+/ ]                    => "Whatever." }
  
  def hey text
    REPERTORY.each do |expression, response|
      return response if expression.reject{|exp| exp =~ text}.empty?
    end
  end

end
