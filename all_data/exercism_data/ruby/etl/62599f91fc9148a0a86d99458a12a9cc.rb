module ETL
  def self.transform(old)
    new = {}

    old.invert.each do |keys, value|
      keys.each { |k| new[k.downcase] = value }
    end

    new
  end
end
