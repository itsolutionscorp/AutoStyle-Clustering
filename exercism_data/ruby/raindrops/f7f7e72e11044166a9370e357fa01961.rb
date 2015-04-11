class Raindrops
  def self.convert(number)
    string = ''

    translations = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    translations.each do |factor, word| 
      if number % factor == 0
        string += word 
      end
    end

    if string == ''
      string = number.to_s
    end

    string
  end
end
