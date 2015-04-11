class Anagram < String

	def match array
		result = array.each_with_object([]) do |item, memo| 
      memo << item if character_count(self) == character_count(item)
    end
  end

  def character_count string
    string.split('').each_with_object(Hash.new(0)) { |key, memo| memo[key] += 1 }
  end

end
