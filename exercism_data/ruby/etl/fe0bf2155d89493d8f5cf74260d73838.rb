class ETL
  def self.transform(extracted)
    load = {}
    extracted.each_pair do |score, letters|
      letters.each_with_object(load) { |letter, h| h[letter.downcase] = score }
    end
    load
  end
end
