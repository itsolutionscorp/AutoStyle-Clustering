class Fixnum
  def to_roman
    self.to_s.chars.reverse.each_with_index.map do |digit, i|
      next if digit.to_i.zero?
      %w(I II III IV V VI VII VIII IX X)[digit.to_i - 1]
        .tr("IVX", %w(IVX XLC CDM M)[i])
    end.reverse.join
  end
end
