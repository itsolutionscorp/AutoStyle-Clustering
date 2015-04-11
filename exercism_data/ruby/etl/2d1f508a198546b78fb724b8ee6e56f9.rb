class ETL
  def self.transform(old)
    old.each_with_object(Hash.new(0)) do |x, o|
      x[1].each { |v| o[v.downcase] = x[0] }
    end
  end
end
