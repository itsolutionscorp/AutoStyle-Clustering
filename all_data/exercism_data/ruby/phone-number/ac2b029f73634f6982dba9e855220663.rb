class PhoneNumber

  def initialize(pn = '')
    @pn = clean pn
    @sms = '0000000000'
    build_sms
  end

  def number
    sms
  end

  def area_code
    sms[0, 3]
  end

  def prefix
    sms[3,3]
  end

  def line
    sms[6,4]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line}"
  end

private
  attr_reader :pn
  attr_accessor :sms

  def build_sms
    return if (invalid_length? || bad_international?)
    if pn.length == 11
      self.sms = pn[1..-1]
    else
      self.sms = pn
    end
  end

  def clean(pn)
    pn.tr('^0-9', '')
  end

  def invalid_length?
    short? || long?
  end

  def short?
    pn.length < 10
  end

  def long?
    pn.length > 11
  end

  def bad_international?
    (pn.length == 11) && (pn[0] != '1')
  end
end
