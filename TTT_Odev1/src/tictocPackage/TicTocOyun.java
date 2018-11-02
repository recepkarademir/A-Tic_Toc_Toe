/*
 *   Ekim  2018
 *   Recep KARADEM�R   2015141003
 *   Tic-Tac-Toe (XOX) Oyununun Java programlama diliyle kodlanmas�
*/
package tictocPackage;


import java.util.Scanner;

public class TicTocOyun
{
	
	// global eri�ilebilir de�i�kenler.
	public static char tahta[][] = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } }; // oyun tahtas�n� harf dolduraca��m�z dizi.
	public static int hamleSayisi = 1;// oyunda toplam 9 kez hamle yap�labilir.
	public static int kazanma = 2; // kazanma == 3 ise hatal� konum tu�lamas� yap�lm��. kazanan == 1 ise bir oyuncu zafer kazand�. kazanan== 2 ise oyun devam ediyor
	public static int hamle=0; // oyuncunun se�ti�i kutu numaras�n� oyun tahtas�na yerle�tirmek i�in kullan�lan ge�ici konum tutucu.
	
	public static void main(String[] args)
	{
		
		System.out.print("Oyun tahtas�nda, harfinizi koymak istedi�iniz\n");
		System.out.print("alan�n i�indeki rakam� tu�lay�n.\n");
		System.out.println("\n\n\n");
		
		while(hamleSayisi <10 && kazanma == 2) // oyunda toplam 9 kez hamle yap�labilir. Kazanan==2 de�ilse oyun bitmi�tir.
		{
			tahtaYazdir(); // yazd�rma i�lemi
			tahtaKontrol(); // kurallara uygun konum kontrol� yap�l�yor ve harf tahtaya yerle�tiriliyor.
			
			if(kazanma==3) // harf yerle�tirmede problem varsa tekrar konum isteniyor.
			{
				while(true)// konumun hatal� veya �nceden se�ili konuma tekrar harf girilmemesi i�in yaz�lan while d�ng�s�.
				{
					System.out.println("\n\n\n");
					tahtaYazdir(); // hatal� konum yerine, �nceden se�ilmemi� ve [1..9] aras�  konum de�eri tekrar isteniyor.
					tahtaKontrol(); // kurallara uygun konum kontrol� yap�l�yor ve harf tahtaya yerle�tiriliyor.
					if(kazanma == 2) // kurallara uygun konum girildiyse oyun devam ediyor. Girilmediyse tekrar konum girilmesi isteniyor.
					{
						break; // konum do�ru girildiyse while dan ��k�p, kazanan konrol�ne ge�ilebilir.
					}
				}
			}
			
			// harf yerle�tirmede problem yoksa kazanan kontrol� yap�lmal�.
			kazananKontrol(); // kazanan varsa yazd�rma i�lemi farkl� i�leyecek. oyunda kazanan�n olup olmad��� kontrol� yap�l�yor.
			if(kazanma == 2) // konum do�ru girilmi�, harf yerle�tirilmi� ve kazanan kontrol� yap�lm�� demek.Oyun devam edecekse bu if �al���r.
			{
				hamleSayisi++;// kazanan olsa da olmasa da hamle say�s� art�r�lmal� ��nk� bir hamle tamamland�.
				System.out.println("\n\n\n");
			}
			else if(kazanma==1)// kazanan tespit fonksiyonu, kazanan oldu�unu kazanma de�i�kenini 1 yaparak belirtirse bu ko�ul �al���r.
			{
				System.out.println("\n\n\n");
				tahtaYazdir(); // kazanan� yazd�rma i�lemi
				break;
			}
		}
		
		if(hamleSayisi==10) // oyunda kazanan varsa buray� yazd�rmamak i�in hamle kontrol� yap�l�r. Buras� sadece beraberlik durumunda yazd�r�l�r.
		{
			kazanma=0; // b�t�n hamleler yap�ld�ysa, berabere bitti�ini yazd�rmak i�in kazanma ==0 olmal�.
			tahtaYazdir(); // hamle say�s� 9 olduysa beraberlik yazd�rma i�lemi
		}
	}
	
	
	static void tahtaYazdir() // kazanan true ise oyunu bitir
	{
		Scanner oku = new Scanner(System.in); // harf yerle�tirilecek konumu kullan�c�dan
		System.out.print("\n\tXOX Oyunu\t\n\n\n");
		System.out.print("1.Oyuncunun harf simgesi : X\n2.Oyuncunun harf simgesi : O\n\n");
		System.out.println("Se�ti�iniz rakam�n yerine kendi harf simgenizi koyars�n�z.\n ");
		System.out.print("     |     |     \n");
		System.out.print("  " + tahta[0][0] + "  |  " + tahta[0][1] + "  |  " + tahta[0][2] + "  \n");
		System.out.print("_____|_____|_____\n");
		System.out.print("     |     |     \n");
		System.out.print("  " + tahta[1][0] + "  |  " + tahta[1][1] + "  |  " + tahta[1][2] + "  \n");
		System.out.print("_____|_____|_____\n");
		System.out.print("     |     |     \n");
		System.out.print("  " + tahta[2][0] + "  |  " + tahta[2][1] + "  |  " + tahta[2][2] + "  \n");
		System.out.print("     |     |     \n\n");
		
		if(kazanma == 2 || kazanma==3) // oyuna devam edilecek. Hatal� konum girildiyse veya oyun devam etmesi gerekiyorsa bu ko�ul �al���r.
		{
			if(hamleSayisi % 2 != 0) // hamleSayisi de�i�keni 1 den ba�l�yor. 1.Oyuncudan. Tek rakamlarda s�ra 1. oyuncuda
			{
				System.out.println("Oyuncu 1, rakam giriniz :");
			}
			else // hamlesay�s�  �ift rakam ise s�ra ikinci oyuncudad�r.
			{
				System.out.println("Oyuncu 2, rakam giriniz :");
			}
			
			hamle = oku.nextShort();
			return ; // program main e d�nmeli.
		}
		else if(kazanma == 1) // kazanan olursa 9 kez hamle beklemeye gerek yok.
		{
			if(hamleSayisi % 2 != 0)// hamleSayisi tek ise s�ra  1. oyuncuda, �ift ise 2.oyuncudad�r.
			{
				System.out.print("==> 1. oyuncu ( X ) oyunu kazand�");
			}
			else if(hamleSayisi % 2 == 0)// hamleSayisi tek ise s�ra  1. oyuncuda, �ift ise 2.oyuncudad�r.
			{
				System.out.print("==> 2. oyuncu ( O ) oyunu kazand�");
			}
			return ;// program main e d�nmeli.
		}
		else if(kazanma == 0) // 0 kodu beraberlik kodudur.
		{
			System.out.println("Oyun berabere bitti ! ");
			return ;// program main e d�nmeli.
		}
	}
	
	
	static void tahtaKontrol()
	{

		if(hamle > 9 || hamle < 1) // konum de�eri oyun tahtas�ndaki de�i�kenlerden biri olmal�.
		{
			System.out.print("\n\nSe�ti�iniz yer oyun alan� d���nda !");
			kazanma =3 ;//hatal� tahta konumu se�ildi�inde kazanma 3 de�erini alacak.
		}
		else
		{
			char harf;
			// oyuncu s�ras�na bakarak hangi harfin yerle�tirilmesi gerekti�i tespit ediliyor.
			if(hamleSayisi % 2 != 0)
			{
				harf = 'X';
			}
			else
			{
				harf = 'O';
			}
			// Harf yerle�tirilmesi yap�lmadan �nce, yerle�im yap�lacak yerin bo� olup olmad��� kontrol edilir. X ve O olmayacak.
			switch(hamle) // oyun tahtas�ndaki se�ili yere, �uanki harf yerle�tiren oyuncunun harfi yerle�tirilir.
			{
				case 1:
					if(tahta[0][0] =='1') // buras� 1 ise yerle�tirme yap�labilir.
					{
						tahta[0][0] = harf;kazanma=2; // do�ru bir konum de�eri girildiyse harf yerle�tirme ba�ar�l� oldu�unu bildiren 2 de�eri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSe�ti�iniz yere �nceden harf yerle�tirilmi� !");
						kazanma =3;
					}
					break;
				case 2:	
					if(tahta[0][1] =='2')
					{
						tahta[0][1] = harf;kazanma=2; // do�ru bir konum de�eri girildiyse harf yerle�tirme ba�ar�l� oldu�unu bildiren 2 de�eri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSe�ti�iniz yere �nceden harf yerle�tirilmi� !");
						kazanma =3;
					}
					break;
				case 3:
					if(tahta[0][2] =='3')
					{
						tahta[0][2] = harf;kazanma=2; // do�ru bir konum de�eri girildiyse harf yerle�tirme ba�ar�l� oldu�unu bildiren 2 de�eri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSe�ti�iniz yere �nceden harf yerle�tirilmi� !");
						kazanma =3;
					}
					break;
				case 4:
					if(tahta[1][0] =='4')
					{
						tahta[1][0] = harf;kazanma=2;// do�ru bir konum de�eri girildiyse harf yerle�tirme ba�ar�l� oldu�unu bildiren 2 de�eri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSe�ti�iniz yere �nceden harf yerle�tirilmi� !");
						kazanma =3;
					}
					break;
				case 5:
					if(tahta[1][1] =='5')
					{
						tahta[1][1] = harf;kazanma=2;// do�ru bir konum de�eri girildiyse harf yerle�tirme ba�ar�l� oldu�unu bildiren 2 de�eri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSe�ti�iniz yere �nceden harf yerle�tirilmi� !");
						kazanma =3;
					}
					break;
				case 6:
					if(tahta[1][2] =='6')
					{
						tahta[1][2] = harf;kazanma=2;// do�ru bir konum de�eri girildiyse harf yerle�tirme ba�ar�l� oldu�unu bildiren 2 de�eri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSe�ti�iniz yere �nceden harf yerle�tirilmi� !");
						kazanma =3;
					}
					break;
				case 7:
					if(tahta[2][0] =='7')
					{
						tahta[2][0] = harf;kazanma=2;// do�ru bir konum de�eri girildiyse harf yerle�tirme ba�ar�l� oldu�unu bildiren 2 de�eri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSe�ti�iniz yere �nceden harf yerle�tirilmi� !");
						kazanma =3;
					}
					break;
				case 8:
					if(tahta[2][1] =='8')
					{
						tahta[2][1] = harf;kazanma=2;// do�ru bir konum de�eri girildiyse harf yerle�tirme ba�ar�l� oldu�unu bildiren 2 de�eri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSe�ti�iniz yere �nceden harf yerle�tirilmi� !");
						kazanma =3;
					}
					break;
				case 9:
					if(tahta[2][2] =='9')
					{
						tahta[2][2] = harf;kazanma=2;// do�ru bir konum de�eri girildiyse harf yerle�tirme ba�ar�l� oldu�unu bildiren 2 de�eri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSe�ti�iniz yere �nceden harf yerle�tirilmi� !");
						kazanma =3;
					}
					break;
			}
		}	
	}
	
	
	static void kazananKontrol()// kazanan varsa, kazanan�n oldu�unu bildiren de�i�imi yap�yor.
	{
		// e�er alanlardaki 3 l� harfler ayn�ysa, kazanma 1 de�erine sahip olacak.
		
		// �arpraz e�itlik i�in kontrol				
		if(     tahta[0][0] == tahta[1][1] && tahta[1][1] == tahta[2][2]     ) // 1 nural� yer == 5 numaral� yer == 9 numaral� yer.
		{
			kazanma = 1; // 1numaral� b�lge 5 numaral� b�lge ve 9 numaral� b�lge ayn� harfse oyunu bir oyuncu kazand�.
		}
		else if(tahta[0][2] == tahta[1][1] && tahta[1][1] == tahta[2][0])
		{
			kazanma = 1;
		}
		// dikey e�itlik i�in kontrol
		else if(tahta[0][0] == tahta[1][0] && tahta[1][0] == tahta[2][0])
		{
			kazanma = 1;
		}
		else if(tahta[0][1] == tahta[1][1] && tahta[1][1] == tahta[2][1])
		{
			kazanma = 1;
		}
		else if(tahta[0][2] == tahta[1][2] && tahta[1][2] == tahta[2][2])
		{
			kazanma = 1;
		}
		//yatay e�itlik i�in kontrol
		else if(tahta[0][0] == tahta[0][1] && tahta[0][1] == tahta[0][2])
		{
			kazanma = 1;
		}
		else if(tahta[1][0] == tahta[1][1] && tahta[1][1] == tahta[1][2])
		{
			kazanma = 1;
		}
		else if(tahta[2][0] == tahta[2][1] && tahta[2][1] == tahta[2][2])
		{
			kazanma = 1;
		}
		// e�itlik olmazsa oyun devam edecek.
		else
		{
			kazanma = 2; // kazanan yoksa devam anlam�nda 2 kodunu g�nderecek.
		}
	}
}