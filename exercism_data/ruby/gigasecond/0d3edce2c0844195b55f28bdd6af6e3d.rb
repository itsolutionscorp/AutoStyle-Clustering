class Gigasecond

  def self.from(time)
    giga = time + 10**9
    if (giga-3600).dst? != (giga).dst?
      giga -= 3600
    elsif (giga+3600).dst? != (giga).dst?
      giga -= 3600
    end
    return giga
  end
end
