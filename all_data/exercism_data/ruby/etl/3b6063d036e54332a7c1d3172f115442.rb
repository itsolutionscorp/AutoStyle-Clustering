class ETL

  def self.transform(source)
    target = {}
    source.each_pair do |num, value|
      value.each { |char| target[char.downcase] = num }
    end
    target
  end
end
