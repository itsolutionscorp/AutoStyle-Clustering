class Fixnum
    ROMAN_TEMPLATES = {'0' => '',
                       '1' => 'U',
                       '2' => 'UU',
                       '3' => 'UUU',
                       '4' => 'UF',
                       '5' => 'F',
                       '6' => 'FU',
                       '7' => 'FUU',
                       '8' => 'FUUU',
                       '9' => 'UN'}
    ROMAN_POSITIONS = {1 => {'U'=>'I', 'F'=>'V', 'N'=>'X'},
                       2 => {'U'=>'X', 'F'=>'L', 'N'=>'C'},
                       3 => {'U'=>'C', 'F'=>'D', 'N'=>'M'},
                       4 => {'U'=>'M'}}

    def to_roman
        self.to_s.chars.reverse.each_with_index.map do |d, i|
            template = ROMAN_TEMPLATES[d]
            position = ROMAN_POSITIONS[i+1]
            template.gsub(/./, position)
        end.reverse.join
    end
end
