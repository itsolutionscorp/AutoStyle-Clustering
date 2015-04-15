class Gigasecond

  def self.from(input)
    input = input.to_time
    input += 10**9
    input = input.to_date
    puts "#{input}"
    input
  end



end
