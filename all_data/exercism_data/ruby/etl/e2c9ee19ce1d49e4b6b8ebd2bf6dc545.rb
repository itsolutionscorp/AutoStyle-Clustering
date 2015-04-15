class ETL

  def self.transform(old)
    response = Hash.new { |response, key| response[key] = 0 }

    old.each do |digit , letters|
      letters.each { |letter| response[letter.downcase] = digit }
    end
    response
  end

end
