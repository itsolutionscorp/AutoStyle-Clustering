class SecretHandshake
  
  GESTURES = ["wink","double blink","close your eyes","jump"]

  def initialize(number)
    number = 0 unless number.kind_of?(Integer)
    @number = "%b" % number
  end

  def commands
    result = []
    GESTURES.each_with_index do |item, index|
      result << item if @number.chars.reverse[index] == "1"
    end
    @number.size == 5 && @number[0] == "1" ? result.reverse : result
  end

end
