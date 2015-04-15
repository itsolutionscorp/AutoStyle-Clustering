class Raindrops
  def self.convert(input)
    output = ''
    if plinger?(input)
      output += 'Pling'
    end
    if planger?(input)
      output += 'Plang'
    end
    if plonger?(input)
      output += 'Plong'
    end

    if output == ''
      output += input.to_s
    end

    output
  end

  private
  def self.plinger?(input)
    input % 3 == 0
  end

  def self.planger?(input)
    input % 5 == 0
  end

  def self.plonger?(input)
    input % 7 == 0
  end
end
