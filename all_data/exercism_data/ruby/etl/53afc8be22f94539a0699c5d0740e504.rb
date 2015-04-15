class ETL
  def self.transform(old_format)
    new_format = Hash.new
    old_format.each do |score,letters|
      letters.map{|letter| new_format[letter.downcase] = score}
    end
    new_format
  end
end
