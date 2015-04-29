class ETL
  def self.transform(old_data)
    new_data = Hash.new

    old_data.each_pair do |score, letters|
      letters.each { |letter| new_data[letter.downcase] = score }
    end

    new_data
  end
end
