class Bob

  #Blank = ->(input) { input.strip == '' }
  Shouting = ->(input) { (input =~ /[A-Z]/) && !(input =~ /[a-z]/) }
  Question = ->(input) { input =~ /\?\Z/ }
  Default = ->(_) { true }
  
  Blank = lambda do |input|
	  input.strip == '' 
  end

  MAPPINGS = {
    Blank => 'Fine. Be that way!',
    Shouting => 'Woah, chill out!',
    Question => 'Sure.',
    Default => 'Whatever.',
  }

  def hey(input)
    MAPPINGS.detect { |proc, _| proc[input] }.last
  end
end
