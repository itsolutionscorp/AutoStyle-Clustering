class ETL

  def self.transform(old_data)
    ETL.new(old_data).transform
  end

  attr_reader :old_data, :new_data

  def initialize(old_data)
    @old_data = old_data.invert
    @new_data = {}
  end

  def transform
    old_data.keys.each do |key|
      write_new_key(key)
    end
    new_data
  end

  def write_new_key(old_key)
    old_key.each do |letter|
      new_data[letter.downcase] = old_data[old_key]
    end
  end
end
