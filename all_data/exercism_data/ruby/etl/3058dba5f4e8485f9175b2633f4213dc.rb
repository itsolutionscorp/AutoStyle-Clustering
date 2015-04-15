class ETL
  def self.transform(old)
    response = Hash.new { |response, key| response[key] = 0 }
    old.keys.each do |digit|
      old[digit].each { |letter| response[letter.downcase] = digit }
    end
    response

  end


end
