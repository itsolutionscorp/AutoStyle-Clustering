class ETL

  def self.transform(source)
    target = {}
    source.each_pair do |num, value|
      value.each do |char|
        target[char.downcase] = num
      end
    end
    target
  end
end
