class Raindrops
  def self.convert(a_number)
    translated_string = ''
    translated_string << 'Pling' if a_number % 3 == 0
    translated_string << 'Plang' if a_number % 5 == 0
    translated_string << 'Plong' if a_number % 7 == 0
    return (translated_string.empty?() ? a_number.to_s : translated_string)
  end
end
