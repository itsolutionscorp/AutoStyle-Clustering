class ETL

  def self.transform old
    @new = {}
    old.each { |key,value| invert(key,value) }
    @new
  end

  def self.invert(key,value)
    value.each { |word| @new[word.downcase] = key }
    @new
  end

end
