class Gigasecond

  def self.from(x)

    gs = 1000000000
    sec_p_min = 60
    sec_p_hr = sec_p_min * 60
    sec_p_day = sec_p_hr * 24
    sec_p_yr = sec_p_day * 365

    days = gs / sec_p_day

    date = x + days

    return date
  end

end
