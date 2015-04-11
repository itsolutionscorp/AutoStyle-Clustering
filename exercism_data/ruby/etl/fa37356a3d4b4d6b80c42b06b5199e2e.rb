class ETL

  def self.transform(oldData)
    newData = {}
    oldData.each do |x,y|
        y.each do |value|
            newData[value.downcase] = x
        end
    end
    return newData
  end
end
