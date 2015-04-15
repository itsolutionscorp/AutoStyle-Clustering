class ETL
  def self.transform(old)
    new = Hash.new
    old.each do |points, chars|
      chars.each do |char|
        new[char.downcase] = points
      end
    end
    return new
  end
end
