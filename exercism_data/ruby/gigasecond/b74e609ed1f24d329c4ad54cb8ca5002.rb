# Calculates gigasecond 'anniversary'
require 'date'

class Gigasecond
  def self.from(input_date)
    output_date = input_date.strftime("%s").to_i
    output_date += 10**9
    output_date = output_date.to_s
    Date.strptime(output_date,"%s")
  end
end
