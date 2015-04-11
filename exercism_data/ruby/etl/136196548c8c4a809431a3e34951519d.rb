module Legacy
  class LetterNotFoundError < StandardError; end

  class ScrableData
    attr_reader :data
    def initialize(data)
      @data = data
    end

    # Returns a sorted, unique list of letters in the data set
    def letters
      data.values.flatten.uniq.sort
    end

    # Returns the point value for a letter, if the letter is not found
    # raises an exception
    def value_for_letter(letter)
      data.each_pair do |point_value, letters|
        return point_value if letters.include? letter 
      end
      raise LetterNotFoundError
    end
  end
end

class ScrableData
  def self.from_legacy_scrable_scrable_data(legacy_data)
    ScrableData.new.tap do |scrable_data|
      legacy_data.letters.each do |letter|
        scrable_data.add_letter(letter.downcase, 
                                legacy_data.value_for_letter(letter))
      end 
    end
  end

  attr_accessor :data

  def initialize(data = {})
    @data = {}
  end

  def add_letter(letter, score)
    data[letter] = score
  end

  def to_hash
    data
  end
end

module ETL
  # Expects a hash where the key is the point value and the value is the
  # letters that map to that value, eg: { 1 => ['A', 'B', 'C'] }
  # Returns a hash where the key is the letter and the value is the
  # point value, eg: { 'a' => 1, 'b' => 2, c => 3 }
  def self.transform(hash_of_values)
    legacy_scrable_data = Legacy::ScrableData.new(hash_of_values)
    new_data = ScrableData.from_legacy_scrable_scrable_data(legacy_scrable_data)
    new_data.to_hash
  end
end
