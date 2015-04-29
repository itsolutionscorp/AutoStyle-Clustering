class ETL
  def self.transform scores
    ret = {}
    scores.each do |point,letters|
      letters.each do |letter|
        ret[letter.downcase] = point
      end
    end
    ret
  end
end
