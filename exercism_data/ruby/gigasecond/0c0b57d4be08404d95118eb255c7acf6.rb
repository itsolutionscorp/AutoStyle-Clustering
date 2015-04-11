class Gigasecond

    gs = 10**9
    sec_p_min = 60
    sec_p_hr = sec_p_min * 60
    sec_p_day = sec_p_hr * 24

    DAYS_IN_GS = gs / sec_p_day

  def self.from(start_date)

    end_date = start_date + DAYS_IN_GS
    end_date

  end

end
