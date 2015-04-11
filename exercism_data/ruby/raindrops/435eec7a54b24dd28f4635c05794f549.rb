class Raindrops
  @@dictionary = {   3 => 'Pling',  5 => 'Plang',   7 => 'Plong'}

  def self.convert(a_number)
    # Iterate through the hash, taking advantage of the fact that hash keys are returned in the order
    # in which they are inserted into the hash, and so we'll build up the translated string in the right order
    translated_string = (@@dictionary.map { |prime_factor, translation| a_number % prime_factor == 0 ? translation : ''}).join
    return (translated_string == '' ? a_number.to_s : translated_string)
  end
end
