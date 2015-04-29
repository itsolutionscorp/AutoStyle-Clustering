numeracion_th = [''] + 'first second third fourth fifth sixth seventh eighth ninth tenth eleventh twelfth'.split()
numeracion = [''] + 'a two three four five six seven eight nine ten eleven twelve'.split()

formatear = 'On the {th} day of Christmas my true love gave to me, {elementos}.\n'

todos_los_elementos = [
    'Partridge in a Pear Tree',
    'Turtle Doves',
    'French Hens',
    'Calling Birds',
    'Gold Rings',
    'Geese-a-Laying',
    'Swans-a-Swimming',
    'Maids-a-Milking',
    'Ladies Dancing',
    'Lords-a-Leaping',
    'Pipers Piping',
    'Drummers Drumming'
]
        
def verse(n):
    th = numeracion_th[n]
    elementos = [numeracion[i+1] + ' ' + todos_los_elementos[i] for i in range(n)]
    elementos = list(reversed(elementos))
    elementos_formateado = ', '.join(elementos[:-1]) + ', and ' + elementos[-1] \
            if len(elementos) != 1 else elementos[0]
    return formatear.format(elementos=elementos_formateado, th=th)

def verses(inicio, final):
    return '\n'.join([verse(i) for i in range(inicio, final+1)]) + '\n'

def sing():
    return verses(1, len(todos_los_elementos))
