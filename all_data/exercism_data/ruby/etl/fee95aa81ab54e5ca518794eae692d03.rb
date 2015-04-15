class ETL
  def initialize
  end

  def self.transform(old)
    test = {}
    old.each do |key,value|
      value.each do |letter|
        test[letter.downcase] = key
      end
    end
    test
  end


end
