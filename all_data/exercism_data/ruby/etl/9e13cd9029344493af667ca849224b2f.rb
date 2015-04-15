class ETL
  def self.transform dictionary
    swapped = dictionary.each_pair.map do |value, letters|
      letters.map{|letter| [letter.downcase, value]}
    end

    Hash[*swapped.flatten]
  end
end
