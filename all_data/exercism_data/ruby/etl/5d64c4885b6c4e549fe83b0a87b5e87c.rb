class ETL
  def self.transform(v1)
    v2 = Hash.new { |h,k| h[k] = {} }
    v1.each do |points,letters|
      letters.each do |letter|
        v2[letter.downcase] = points
      end
    end
    v2
  end
end
