class Phone
  PHONE_NUMBER_PATTERN = /^
    (?:
      (?<prefix>\d)              # prefix digit
      [ \-\.]?                   # optional separator
    )?
    (?:
      \(?(?<area_code>\d{3})\)?  # area code
      [ \-\.]?                   # optional separator
    )?
    (?<trunk>\d{3})              # trunk
    [ \-\.]?                     # optional separator
    (?<line>\d{4})               # line
  $/x

  def initialize(candidate_number)
    decompose(candidate_number)
  end

  def area_code
    @area_code || '000'
  end

  def trunk
    @trunk || '000'
  end

  def line
    @line || '0000'
  end

  def number
    "#{area_code}#{trunk}#{line}"
  end

  def to_s
    "(#{area_code}) #{trunk}-#{line}"
  end

  private
  def decompose(candidate_number)
    PHONE_NUMBER_PATTERN.match(candidate_number) do |match|
      return unless match[:prefix].nil? || match[:prefix] == '1'
      @area_code = match[:area_code]
      @trunk     = match[:trunk]
      @line      = match[:line]
    end
  end
end
