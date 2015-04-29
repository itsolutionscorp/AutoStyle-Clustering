class Gigasecond

  def self.from (birthday)
    t = Time.parse(birthday.to_s)
    Date.parse((t+10**9).to_s)
  end

end
