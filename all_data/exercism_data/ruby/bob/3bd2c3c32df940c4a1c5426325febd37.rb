class Bob
  REPERTORY =  { [ /\A[^a-z]*\z/, /[A-Z]+/ ]   => "Whoa, chill out!",
                 [ /\?\z/ ]                    => "Sure.",
                 [ /\A[[:space:]]*\z/ ]        => "Fine. Be that way!",
                 [ /\w+/ ]                     => "Whatever." }
  
  def hey text
    response = REPERTORY.detect do |expressions, response|
       expressions.all?{|exp| exp =~ text}
    end
    response.last
  end

end
