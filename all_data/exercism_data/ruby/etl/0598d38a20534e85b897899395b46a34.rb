class ETL

  def self.transform old
    parser old
  end

private

  def self.parser old
    old.invert.each_with_object( Hash.new ) do |( numbers, value ), result| 
      numbers.each { |element| result[ element.downcase ] = value } 
    end
  end
  
end
