class Bob
  def hey(greeting)
    Bob.respond_to(greeting)
  end

  def self.respond_to(greeting)
    case 
    when greeting.empty?
      'Fine. Be that way.'
    when greeting == greeting.upcase
     "Woah, chill out!"
    when greeting.end_with?("?")
     'Sure.'
    else
      "Whatever."
    end
  end
end
