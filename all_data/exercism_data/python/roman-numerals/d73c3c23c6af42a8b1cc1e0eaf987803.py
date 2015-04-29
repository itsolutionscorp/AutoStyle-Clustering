def numeral(arabic):
    ones = ["","I","II","III","IV","V","VI","VII","VIII","IX"]
    tens = ["","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"]
    hundreds = ["","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"]
    thousands = ["","M","MM","MMM"]
    conv_list = [ones,tens,hundreds,thousands]
    ara_list = list(str(arabic)[::-1])
    rom_list = [conv_list[int(i)][int(v)] for i,v in enumerate(ara_list)]
    return "".join(rom_list[::-1])
