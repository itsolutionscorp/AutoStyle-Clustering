class ETL

  def ETL.transform input
    output = {}
    input.each_pair{ | key, value | value.each{ | element | output[element.downcase] = key } }
    return output
  end

end
