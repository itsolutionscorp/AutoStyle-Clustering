class SecretHandshake  
  attr_reader :negative, :source

  def initialize(mark)
    if mark.kind_of?(Fixnum)
      binary = mark.to_s(2).reverse
      @source = binary[0..3]
      @negative = (binary[4] == "1")
    else
      @source = ""
    end
  end

  def actions
    {
      "1"    => "wink",
      "10"   => "double blink",
      "100"  => "close your eyes",
      "1000" => "jump"
    }
  end 

  def actions_for(data)
    results = []
    source.chars.each_with_index do |numeral, index|
      if numeral == "1"
        key = numeral + ("0" * index)
        results << actions[key]
      end
    end
    results
  end

  def negative?
    negative
  end

  def commands
    if negative?
      actions_for(source).reverse
    else
      actions_for(source)
    end
  end
end
