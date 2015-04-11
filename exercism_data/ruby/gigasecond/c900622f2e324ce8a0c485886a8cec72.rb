class Gigasecond
  A_GIGASECOND = 1000000000
  def self.from (arg)
    (arg.to_time + A_GIGASECOND).to_date
  end
end

# I see that a constant would be more clear
# than computation or instance variable
