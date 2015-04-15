# = etl.rb
# Author::     Ricardo Gonzalez Saldivar
# Web::        rigsald.net
#
# == Bob Class
# _Bob_ class was designed during the
# exercism.io exercises.
class ETL
  def self.transform(oldData)
    newData = {}
    oldData.each do |x, y|
      y.each do |value|
        newData[value.downcase] = x
      end
    end
    return newData
  end
end
