class Gigasecond

  SECONDS = 1000000000 # 1 Gigasecond == 10**9 seconds

  def initialize(date_of_birth)
    if date_of_birth.kind_of?(Date)
      @date_of_birth = date_of_birth
    end
  end

  # Calculates date of the 1Gs party.
  def date
    return unless @date_of_birth.kind_of?(Date)

    date_of_birth_in_sec = @date_of_birth.strftime('%s').to_i
    date_of_party_in_sec = date_of_birth_in_sec + SECONDS
    datetime_party       = DateTime.strptime("#{date_of_party_in_sec}", '%s')

    datetime_party.to_date
  end

end
