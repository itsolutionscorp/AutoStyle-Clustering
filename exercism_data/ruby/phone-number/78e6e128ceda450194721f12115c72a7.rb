class PhoneNumber
  attr_accessor :strand

  def initialize strand
    @strand = strand
  end

  def number
    return '0000000000' if strand.match(/[a-zA-Z]/)
    fixme = strand.gsub(/\D/, '')
    fixme.slice!(0) if (fixme.size >= 11) && fixme.start_with?('1')
    valid?(fixme) ? fixme : '0000000000'
  end

  def valid? blerb
    if blerb.size == 10
      true
    elsif blerb.size >= 11
      false
    elsif blerb.size < 10
      false
    end
  end

  def area_code
    area = number
    area[0..2]
  end

  def to_s
    pretty = number
    pretty.insert(0, '(').insert(4, ')').insert(5, ' ').insert(9, '-')
    pretty
  end
end

#puts PhoneNumber.new("11234567890").to_s
