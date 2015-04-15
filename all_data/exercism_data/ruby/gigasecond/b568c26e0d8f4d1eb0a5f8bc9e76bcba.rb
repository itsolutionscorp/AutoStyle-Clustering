class Gigasecond
  def self.from(birthday)
    if birthday.class == Date
      gs = birthday + 10**9 / (24 * 60 * 60)
    else
      gs = birthday + 10**9
      # Hmm, cannot see how to convert Time object to Date object.
      # Won't pass test 4!!
    end

    gs
  end
end
