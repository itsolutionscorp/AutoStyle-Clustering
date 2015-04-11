class Fixnum
  def to_roman
    self.to_s.chars.reverse.each_with_index.map do |digit, i|
      next if digit.to_i.zero?
      roman = %w(I II III IV V VI VII VIII IX X)[digit.to_i - 1]

      case i
      when 0 then roman
      when 1 then roman.tr("IVX", "XLC")
      when 2 then roman.tr("IVX", "CDM")
      when 3 then roman.tr("I", "M")
      end
    end.reverse.join
  end
end
